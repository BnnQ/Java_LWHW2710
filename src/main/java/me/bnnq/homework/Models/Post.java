package me.bnnq.homework.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String header;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;

    private Timestamp date = new Timestamp(System.currentTimeMillis());

}
