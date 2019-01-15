package com.verticalparts;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.verticalparts.domain.Categoria;
import com.verticalparts.domain.Cidade;
import com.verticalparts.domain.Cliente;
import com.verticalparts.domain.Endereco;
import com.verticalparts.domain.Estado;
import com.verticalparts.domain.ItemPedido;
import com.verticalparts.domain.Pedido;
import com.verticalparts.domain.Produto;
import com.verticalparts.domain.enums.TipoCliente;
import com.verticalparts.repositories.CategoriaRepository;
import com.verticalparts.repositories.CidadeRepository;
import com.verticalparts.repositories.ClienteRepository;
import com.verticalparts.repositories.EnderecoRepository;
import com.verticalparts.repositories.EstadoRepository;
import com.verticalparts.repositories.ItemPedidoRepository;
import com.verticalparts.repositories.PedidoRepository;
import com.verticalparts.repositories.ProdutoRepository;

@SpringBootApplication
public class AulamcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AulamcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Rolos & Roldanas");
		Categoria cat2 = new Categoria(null, "Degraus & Pallets");
		
		Produto p1 = new Produto(null, "VPER-157", "S9500", "Schindler", "Roldana", "Descrição teste");
		Produto p2 = new Produto(null, "VPER-57", "UBS", "OTIS", "Degrau", "Descrição teste");
		Produto p3 = new Produto(null, "VPER-17", "S9300", "Schindler", "Rolo", "Descrição teste");
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade (null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Paulo", "paulo@gmail.com", "232352323", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("995242301", "25617877"));
		
		Endereco e1 = new Endereco(null, "Rua Antonio Peixoto", "8", "Casa", "Jd. Marcelo Torres", "07785-404", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Peixoto Marcelo", "90", "Casa", "Jd. Alvaro Torres", "05455-404", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("20/04/2018 08:44"), cli1, e2);
		Pedido ped2 = new Pedido(null, sdf.parse("11/11/2018 18:20"), cli1, e1);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p2, 7);
		ItemPedido ip2 = new ItemPedido(ped2, p3, 20);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p2.getItens().addAll(Arrays.asList(ip1));
		p3.getItens().addAll(Arrays.asList(ip2));	
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
	}

}

