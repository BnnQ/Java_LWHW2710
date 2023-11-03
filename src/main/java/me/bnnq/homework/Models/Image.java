package me.bnnq.homework.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "post_id")
    private Post post;
}