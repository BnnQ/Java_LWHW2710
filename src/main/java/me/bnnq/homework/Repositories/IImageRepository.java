package me.bnnq.homework.Repositories;

import me.bnnq.homework.Models.Image;
import org.springframework.data.repository.CrudRepository;

public interface IImageRepository extends CrudRepository<Image, Long>
{

}