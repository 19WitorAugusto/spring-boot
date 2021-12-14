package com.witoraugusto.springboot;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.witoraugusto.springboot.domain.Categoria;
import com.witoraugusto.springboot.domain.Cidade;
import com.witoraugusto.springboot.domain.Cliente;
import com.witoraugusto.springboot.domain.Endereco;
import com.witoraugusto.springboot.domain.Estado;
import com.witoraugusto.springboot.domain.Pagamento;
import com.witoraugusto.springboot.domain.PagamentoComBoleto;
import com.witoraugusto.springboot.domain.PagamentoComCartao;
import com.witoraugusto.springboot.domain.Pedido;
import com.witoraugusto.springboot.domain.Produto;
import com.witoraugusto.springboot.domain.enums.EstadoPagamento;
import com.witoraugusto.springboot.domain.enums.TipoCliente;
import com.witoraugusto.springboot.repositories.CategoriaRepository;
import com.witoraugusto.springboot.repositories.CidadeRepositoy;
import com.witoraugusto.springboot.repositories.ClienteRepository;
import com.witoraugusto.springboot.repositories.EnderecoRepository;
import com.witoraugusto.springboot.repositories.EstadoRepositoy;
import com.witoraugusto.springboot.repositories.PagamentoRepository;
import com.witoraugusto.springboot.repositories.PedidoRepository;
import com.witoraugusto.springboot.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categorieRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepositoy estadoRepositoy;

	@Autowired
	private CidadeRepositoy cidadeRepositoy;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2763323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2021 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		categorieRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepositoy.saveAll(Arrays.asList(est1, est2));
		cidadeRepositoy.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	}

}
