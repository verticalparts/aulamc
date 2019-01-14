package com.verticalparts;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.verticalparts.domain.Categoria;
import com.verticalparts.domain.Produto;
import com.verticalparts.repositories.CategoriaRepository;
import com.verticalparts.repositories.ProdutoRepository;

@SpringBootApplication
public class AulamcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

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
	}

}

