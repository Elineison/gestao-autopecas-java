from __future__ import annotations

import html
import re
import shutil
import sys
import zipfile
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
TEMPLATE = ROOT / "modelo-paper-uniasselvi.docx"


def xml_escape(value: str) -> str:
    return html.escape(value, quote=True)


def run_xml(text: str, bold: bool = False, italic: bool = False, code: bool = False) -> str:
    props = []
    if bold:
        props.append("<w:b/>")
    if italic:
        props.append("<w:i/>")
    if code:
        props.append('<w:rFonts w:ascii="Courier New" w:hAnsi="Courier New"/>')
        props.append("<w:sz w:val=\"20\"/>")
    rpr = f"<w:rPr>{''.join(props)}</w:rPr>" if props else ""
    preserve = ' xml:space="preserve"' if text.startswith(" ") or text.endswith(" ") else ""
    return f"<w:r>{rpr}<w:t{preserve}>{xml_escape(text)}</w:t></w:r>"


def inline_runs(text: str) -> str:
    parts: list[str] = []
    pattern = re.compile(r"(\*\*.+?\*\*|`.+?`|\*[^*]+\*)")
    pos = 0
    for match in pattern.finditer(text):
        if match.start() > pos:
            parts.append(run_xml(text[pos:match.start()]))
        token = match.group(0)
        if token.startswith("**") and token.endswith("**"):
            parts.append(run_xml(token[2:-2], bold=True))
        elif token.startswith("`") and token.endswith("`"):
            parts.append(run_xml(token[1:-1], code=True))
        elif token.startswith("*") and token.endswith("*"):
            parts.append(run_xml(token[1:-1], italic=True))
        pos = match.end()
    if pos < len(text):
        parts.append(run_xml(text[pos:]))
    return "".join(parts) or run_xml("")


def para_xml(text: str = "", style: str | None = None, align: str | None = None) -> str:
    props = []
    if style:
        props.append(f'<w:pStyle w:val="{style}"/>')
    if align:
        props.append(f'<w:jc w:val="{align}"/>')
    ppr = f"<w:pPr>{''.join(props)}</w:pPr>" if props else ""
    return f"<w:p>{ppr}{inline_runs(text)}</w:p>"


def code_para_xml(text: str) -> str:
    ppr = (
        "<w:pPr>"
        "<w:spacing w:before=\"0\" w:after=\"0\"/>"
        "<w:shd w:fill=\"F2F2F2\"/>"
        "</w:pPr>"
    )
    return f"<w:p>{ppr}{run_xml(text, code=True)}</w:p>"


def bullet_xml(text: str) -> str:
    ppr = (
        "<w:pPr>"
        "<w:pStyle w:val=\"ListParagraph\"/>"
        "<w:ind w:left=\"720\" w:hanging=\"360\"/>"
        "</w:pPr>"
    )
    bullet = run_xml("• ")
    return f"<w:p>{ppr}{bullet}{inline_runs(text)}</w:p>"


def md_to_body(md: str, fill_name: bool) -> str:
    if fill_name:
        md = md.replace("[Seu nome completo]", "Elineison Inácio")

    blocks: list[str] = []
    in_code = False

    for raw_line in md.splitlines():
        line = raw_line.rstrip()

        if line.startswith("```"):
            in_code = not in_code
            continue

        if in_code:
            blocks.append(code_para_xml(line))
            continue

        if not line:
            blocks.append(para_xml(""))
            continue

        if line.startswith("# "):
            blocks.append(para_xml(line[2:].strip(), style="Title", align="center"))
        elif line.startswith("## "):
            blocks.append(para_xml(line[3:].strip(), style="Heading1"))
        elif line.startswith("### "):
            blocks.append(para_xml(line[4:].strip(), style="Heading2"))
        elif line.startswith("- [ ] "):
            blocks.append(bullet_xml("[ ] " + line[6:].strip()))
        elif line.startswith("- "):
            blocks.append(bullet_xml(line[2:].strip()))
        elif re.match(r"^\d+\. ", line):
            blocks.append(bullet_xml(line.strip()))
        elif line.startswith("> "):
            blocks.append(para_xml(line[2:].strip(), style="IntenseQuote"))
        else:
            blocks.append(para_xml(line))

    return "\n".join(blocks)


def document_xml(body: str) -> str:
    return f"""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:document xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
    xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
    xmlns:o="urn:schemas-microsoft-com:office:office"
    xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
    xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
    xmlns:v="urn:schemas-microsoft-com:vml"
    xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
    xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
    xmlns:w10="urn:schemas-microsoft-com:office:word"
    xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
    xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
    xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
    xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
    xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
    xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
    mc:Ignorable="w14 wp14">
  <w:body>
    {body}
    <w:sectPr>
      <w:pgSz w:w="11906" w:h="16838"/>
      <w:pgMar w:top="1701" w:right="1134" w:bottom="1134" w:left="1701" w:header="708" w:footer="708" w:gutter="0"/>
      <w:cols w:space="708"/>
      <w:docGrid w:linePitch="360"/>
    </w:sectPr>
  </w:body>
</w:document>
"""


def convert(markdown_path: Path, output_path: Path, fill_name: bool = False) -> None:
    md = markdown_path.read_text(encoding="utf-8")
    body = md_to_body(md, fill_name=fill_name)
    xml = document_xml(body)

    if not TEMPLATE.exists():
        raise FileNotFoundError(f"Modelo não encontrado: {TEMPLATE}")

    output_path.parent.mkdir(parents=True, exist_ok=True)
    shutil.copyfile(TEMPLATE, output_path)

    tmp_path = output_path.with_suffix(".tmp.docx")
    with zipfile.ZipFile(output_path, "r") as zin, zipfile.ZipFile(tmp_path, "w", zipfile.ZIP_DEFLATED) as zout:
        for item in zin.infolist():
            if item.filename == "word/document.xml":
                zout.writestr(item, xml)
            else:
                zout.writestr(item, zin.read(item.filename))

    tmp_path.replace(output_path)


def main() -> int:
    outputs = [
        ("docs/PAPER.md", "entrega/Paper_Gestao_AutoPecas.docx", True),
        ("docs/PPT_SOCIALIZACAO.md", "entrega/Roteiro_PPT_Socializacao.docx", False),
        ("docs/GUIA_ENTREGA_FINAL.md", "entrega/Guia_Entrega_Final.docx", False),
        ("docs/PRINTS_E_EVIDENCIAS.md", "entrega/Prints_e_Evidencias.docx", False),
        ("docs/ROTEIRO_APRESENTACAO_ORAL.md", "entrega/Roteiro_Apresentacao_Oral.docx", False),
    ]

    for source, target, fill_name in outputs:
        convert(ROOT / source, ROOT / target, fill_name=fill_name)
        print(target)

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
