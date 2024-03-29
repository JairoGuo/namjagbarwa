local key = KEYS[1];
local threadId = ARGV[1];

-- key、threadId不存在
if (redis.call('hexists', key, threadId) == 0) then
    return nil;
end;

-- 重入计数器-1
local count = redis.call('hincrby', key, threadId, -1);

-- 删除lock
if (count == 0) then
    redis.call('del', key);
    return nil;
end;