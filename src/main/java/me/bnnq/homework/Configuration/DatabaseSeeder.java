package me.bnnq.homework.Configuration;

import me.bnnq.homework.Models.Image;
import me.bnnq.homework.Models.Post;
import me.bnnq.homework.Repositories.IPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseSeeder
{
    @Bean
    CommandLineRunner initDatabase(IPostRepository postRepository)
    {
        return args -> {
            if (postRepository.count() == 0) {
                List<Image> images1 = new ArrayList<>();
                var post1 = new Post(null, "Introduction to .NET", "In this post, we will discuss the basics of .NET and its importance in modern software development. We will also explore the various tools and frameworks available for .NET developers.", images1, new Timestamp(System.currentTimeMillis()));
                images1.add(new Image(null, "/images/1.jpg", post1));
                postRepository.save(post1);

                List<Image> images2 = new ArrayList<>();
                var post2 = new Post(null, "Getting Started with C#", "In this post, we will dive into the world of C# programming. We will cover the basics of syntax, data types, and control structures. By the end of this post, you will have a solid foundation in C# programming.", images2, new Timestamp(System.currentTimeMillis()));
                images2.add(new Image(null, "/images/2.jpg", post2));
                postRepository.save(post2);

                List<Image> images3 = new ArrayList<>();
                var post3 = new Post(null, "Building Web Applications with ASP.NET", "In this post, we will explore the world of web development with ASP.NET. We will cover the basics of web development, including HTML, CSS, and JavaScript. We will also dive into the various tools and frameworks available for building web applications with ASP.NET.", images3, new Timestamp(System.currentTimeMillis()));
                images3.add(new Image(null, "/images/3.jpg", post3));
                postRepository.save(post3);

                List<Image> images4 = new ArrayList<>();
                var post4 = new Post(null, "Debugging Tips and Tricks", "In this post, we will discuss some tips and tricks for debugging your .NET applications. We will cover various debugging techniques and tools that can help you identify and fix bugs in your code.", images4, new Timestamp(System.currentTimeMillis()));
                images4.add(new Image(null, "/images/4.jpg", post4));
                postRepository.save(post4);

                List<Image> images5 = new ArrayList<>();
                var post5 = new Post(null, "Best Practices for .NET Development", "In this post, we will discuss some best practices for developing .NET applications. We will cover topics such as code organization, naming conventions, and error handling. By following these best practices, you can write cleaner and more maintainable code.", images5, new Timestamp(System.currentTimeMillis()));
                images5.add(new Image(null, "/images/5.jpg", post5));
                postRepository.save(post5);

            }
        };
    }

}
