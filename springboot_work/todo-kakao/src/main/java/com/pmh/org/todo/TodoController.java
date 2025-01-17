package com.pmh.org.todo;

import com.pmh.org.kakao.jpa.KakaoEntity;
import com.pmh.org.kakao.jpa.KakaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@CrossOrigin
public class TodoController {

    private final TodoRepository todoRepository;
    private final KakaoRepository kakaoRepository;

    @PostMapping("save")
    public ResponseEntity<TodoEntity> saveTodo(@RequestBody TodoEntity todo,
                                              @AuthenticationPrincipal UserDetails userDetails
    ) {
//        UserDetails userDetails1 = (UserDetails)
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        KakaoEntity kakaoEntity = kakaoRepository.findByEmail(userDetails.getUsername());
        todo.setKakaoEntity(kakaoEntity);
        return ResponseEntity.ok(todoRepository.save(todo));
    }

    @GetMapping("findall")
    public ResponseEntity<Iterable<TodoEntity>> findAllTodo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if(userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        KakaoEntity kakaoEntity = kakaoRepository.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(todoRepository.findAllByKakaoEntity(kakaoEntity));
    }

}
