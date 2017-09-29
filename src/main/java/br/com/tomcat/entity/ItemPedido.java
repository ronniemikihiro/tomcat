package br.com.tomcat.entity;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_item_pedido
 */
public class ItemPedido implements Entity<Long> {

    private Long id;
    private String nome;
    private Double preco;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
