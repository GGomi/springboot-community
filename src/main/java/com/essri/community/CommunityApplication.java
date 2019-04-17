package com.essri.community;

import com.essri.community.domain.Board;
import com.essri.community.domain.BoardType;
import com.essri.community.domain.User;
import com.essri.community.repository.BoardRepository;
import com.essri.community.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
        return (args) -> {
            User user = userRepository.save(User.builder()
                    .name("essri")
                    .email("essri@gmail.com")
                    .password("test")
                    .build()
            );

            IntStream.rangeClosed(1, 200).forEach(index ->
                    boardRepository.save(Board.builder()
                            .user(user)
                            .title("제목 / " + index)
                            .subTitle("부제목 / " + index)
                            .content("#" + index)
                            .boardType(BoardType.free)
                            .build())
            );
        };
    }
}
