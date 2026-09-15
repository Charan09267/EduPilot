package net.edupilot.mockinterviewservice.service;


import net.edupilot.mockinterviewservice.dto.redis.InterviewContext;
import net.edupilot.mockinterviewservice.service.interfaces.InterviewRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class InterviewRedisServiceImpl implements InterviewRedisService {

    private static final String KEY_PREFIX = "interview:context:";

    private final RedisTemplate<String, Object> redisTemplate;

    public InterviewRedisServiceImpl(
            RedisTemplate<String, Object> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveContext(InterviewContext context) {

        String key = KEY_PREFIX + context.getInterviewId();

        redisTemplate.opsForValue().set(
                key,
                context,
                Duration.ofHours(2)
        );
    }

    @Override
    public InterviewContext getContext(Long interviewId) {

        String key = KEY_PREFIX + interviewId;

        return (InterviewContext) redisTemplate
                .opsForValue()
                .get(key);
    }

    @Override
    public void deleteContext(Long interviewId) {

        String key = KEY_PREFIX + interviewId;

        redisTemplate.delete(key);
    }
}
