package me.bnnq.homework.Repositories;

import me.bnnq.homework.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepository extends CrudRepository<Post, Long>
{

}
