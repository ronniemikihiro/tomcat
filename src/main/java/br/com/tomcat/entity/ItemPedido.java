package br.com.tomcat.entity;

import br.com.tomcat.util.StringUtil;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_item_pedido
 */
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements br.com.tomcat.entity.Entity<Long> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "preco", nullable = false)
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

    public String getPrecoRealMoney() {
        return StringUtil.formatRealMoney(preco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
