const state = {
  produtos: [],
  clientes: [],
  vendas: [],
};

async function api(path, options = {}) {
  const response = await fetch(path, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });
  if (!response.ok) {
    const data = await response.json().catch(() => ({ erro: response.statusText }));
    throw new Error(data.erro || response.statusText);
  }
  if (response.status === 204) return null;
  return response.json();
}

function money(value) {
  return Number(value || 0).toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
}

function renderMetrics() {
  document.getElementById("metricProducts").textContent = state.produtos.length;
  document.getElementById("metricClients").textContent = state.clientes.length;
  document.getElementById("metricSales").textContent = state.vendas.length;
  document.getElementById("metricRestock").textContent = state.produtos.filter((item) => item.precisaReposicao).length;
}

function renderSelects() {
  const clients = document.getElementById("saleClient");
  const products = document.getElementById("saleProduct");
  clients.innerHTML = state.clientes.map((item) => `<option value="${item.id}">${item.nome}</option>`).join("");
  products.innerHTML = state.produtos.map((item) => `<option value="${item.id}">${item.nome} - estoque ${item.quantidadeEstoque}</option>`).join("");
}

function renderProducts() {
  const list = document.getElementById("productList");
  list.innerHTML = state.produtos.map((produto) => `
    <article class="row ${produto.precisaReposicao ? "alert" : ""}">
      <strong>${produto.nome}</strong>
      <span>${produto.codigo} | ${produto.categoria} | ${money(produto.preco)}</span>
      <span>Estoque: ${produto.quantidadeEstoque} | Mínimo: ${produto.estoqueMinimo} ${produto.precisaReposicao ? "| Reposição necessária" : ""}</span>
    </article>
  `).join("");
}

function renderSales() {
  const list = document.getElementById("saleList");
  list.innerHTML = state.vendas.map((venda) => `
    <article class="row">
      <strong>${venda.produto}</strong>
      <span>Cliente: ${venda.cliente}</span>
      <span>${venda.quantidade} un. | Total: ${money(venda.valorTotal)}</span>
    </article>
  `).join("");
}

async function refresh() {
  const [produtos, clientes, vendas] = await Promise.all([
    api("/api/produtos"),
    api("/api/clientes"),
    api("/api/vendas"),
  ]);
  state.produtos = produtos;
  state.clientes = clientes;
  state.vendas = vendas;
  renderMetrics();
  renderSelects();
  renderProducts();
  renderSales();
}

document.getElementById("productForm").addEventListener("submit", async (event) => {
  event.preventDefault();
  await api("/api/produtos", {
    method: "POST",
    body: JSON.stringify({
      nome: document.getElementById("productName").value,
      codigo: document.getElementById("productCode").value,
      categoria: document.getElementById("productCategory").value,
      preco: Number(document.getElementById("productPrice").value),
      quantidadeEstoque: Number(document.getElementById("productStock").value),
      estoqueMinimo: Number(document.getElementById("productMinStock").value),
    }),
  });
  event.target.reset();
  await refresh();
});

document.getElementById("clientForm").addEventListener("submit", async (event) => {
  event.preventDefault();
  await api("/api/clientes", {
    method: "POST",
    body: JSON.stringify({
      nome: document.getElementById("clientName").value,
      documento: document.getElementById("clientDocument").value,
      telefone: document.getElementById("clientPhone").value,
      email: document.getElementById("clientEmail").value,
    }),
  });
  event.target.reset();
  await refresh();
});

document.getElementById("saleForm").addEventListener("submit", async (event) => {
  event.preventDefault();
  const message = document.getElementById("message");
  try {
    const venda = await api("/api/vendas", {
      method: "POST",
      body: JSON.stringify({
        clienteId: Number(document.getElementById("saleClient").value),
        produtoId: Number(document.getElementById("saleProduct").value),
        quantidade: Number(document.getElementById("saleQuantity").value),
      }),
    });
    message.textContent = `Venda registrada: ${money(venda.valorTotal)}`;
    await refresh();
  } catch (error) {
    message.textContent = error.message;
  }
});

document.getElementById("refreshButton").addEventListener("click", refresh);

refresh();
