package com.viborotto.musicapi.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Time duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    //TODO BUILDER E MAPSTRUCT
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
