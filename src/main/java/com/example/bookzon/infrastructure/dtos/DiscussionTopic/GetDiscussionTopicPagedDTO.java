package com.example.bookzon.infrastructure.dtos.DiscussionTopic;

import org.hibernate.query.Page;

import java.util.UUID;

public record GetDiscussionTopicPagedDTO (
        UUID id,
        String title,
        int page,
        int size,

        int number,
        String sortBy,
        String sortOrder
) {}