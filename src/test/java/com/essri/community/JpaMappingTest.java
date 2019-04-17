package com.essri.community;

import com.essri.community.domain.Board;
import com.essri.community.domain.BoardType;
import com.essri.community.domain.User;
import com.essri.community.repository.BoardRepository;
import com.essri.community.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
                .name("essri")
                .password("test")
                .email(email)
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브타이틀")
                .content("테스트테스트")
                .user(user)
                .boardType(BoardType.notice)
                .build());
    }

    @Test
    public void 체크() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("essri"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("서브타이틀"));
        assertThat(board.getContent(), is("테스트테스트"));
        assertThat(board.getBoardType(), is(BoardType.notice));
    }
}
