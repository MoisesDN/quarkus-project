package br.com.moisesdias.hibernate;

import jakarta.annotation.Resources;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/produtos")
public class ProdutosResouce {

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos() {
        return entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
    }
}
