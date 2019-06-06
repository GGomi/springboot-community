package com.essri.community.service;

import com.essri.community.domain.Board;
import com.essri.community.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardFindService {

    private BoardRepository boardRepository;

    public BoardFindService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id"));

        return boardRepository.findAll(pageable);

    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(Board.builder().build());
    }
}
