package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.discussionTopic.CreateDiscussionTopicUseCase;
import com.example.bookzon.application.usecases.discussionTopic.GetDiscussionTopicPagedUseCase;
import com.example.bookzon.application.usecases.discussionTopic.GetDiscussionTopicUseCase;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.CreateDiscussionTopicDTO;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.DiscussionTopicWithTop3CommentsDTO;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.GetDiscussionTopicDTO;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.GetDiscussionTopicPagedDTO;
import com.example.bookzon.infrastructure.security.UserIdStrategy.Factory.UserIdStrategyFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.KeyedPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/discussionTopic")
@Tag(name = "DiscussionTopic", description = "*")
public class DiscussionTopicController {
    private final CreateDiscussionTopicUseCase createDiscussionTopicUseCase;

    private final GetDiscussionTopicPagedUseCase getDiscussionTopicPagedUseCase;

    private final GetDiscussionTopicUseCase getDiscussionTopicUseCase;
    @Autowired
    private UserIdStrategyFactory userIdStrategyFactory;

    public DiscussionTopicController(CreateDiscussionTopicUseCase createDiscussionTopicUseCase,
                                     GetDiscussionTopicPagedUseCase getDiscussionTopicPagedUseCase,
                                     GetDiscussionTopicUseCase getDiscussionTopicUseCase){
        this.createDiscussionTopicUseCase =  createDiscussionTopicUseCase;
        this.getDiscussionTopicPagedUseCase = getDiscussionTopicPagedUseCase;
        this.getDiscussionTopicUseCase = getDiscussionTopicUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createDiscussionTopic(@RequestBody CreateDiscussionTopicDTO data, HttpSession session) {
        var createDiscussionTopic = data.toEntity();
        UUID userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
        createDiscussionTopic.setUser(new User(userId));
        DiscussionTopic discussionTopicResponse = createDiscussionTopicUseCase.execute(createDiscussionTopic);
        return ResponseEntity.ok(discussionTopicResponse.getId());
    }

    @GetMapping("")
    public ResponseEntity<DiscussionTopicWithTop3CommentsDTO> getDiscussionTopic(@RequestParam UUID id){
        Optional<DiscussionTopicWithTop3CommentsDTO> discussionTopicWithTop3CommentsDTO = getDiscussionTopicUseCase.execute(id);

        return discussionTopicWithTop3CommentsDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/paged/user")
    public ResponseEntity<Page<GetDiscussionTopicDTO>> getAllDiscussionTopicsByUserIdPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpSession session
    ) {
        UUID userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        var discussionTopicsPage = getDiscussionTopicPagedUseCase.execute(pageable);
        Page<GetDiscussionTopicDTO> dtoPage = discussionTopicsPage.map(this::mapToDto);
        return ResponseEntity.ok(dtoPage);
    }
    @GetMapping("/paged")
    public ResponseEntity<Page<GetDiscussionTopicDTO>> getAllDiscussionTopicsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        var discussionTopicsPage = getDiscussionTopicPagedUseCase.execute(pageable);
        Page<GetDiscussionTopicDTO> dtoPage = discussionTopicsPage.map(this::mapToDto);
        return ResponseEntity.ok(dtoPage);
    }
    private GetDiscussionTopicDTO mapToDto(DiscussionTopic discussionTopic) {
        return GetDiscussionTopicDTO.fromEntity(discussionTopic);
    }
}
