package app.game;

import app.service.GameApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameCacheScheduler {

    private final CacheManager cacheManager;
    private final GameApiService gameApiService;

    @Scheduled(cron = "0 0 0 * * *")
    public void clearGameCaches() {

        System.out.println("CRON JOB: Clearing game caches...");

        clearCache("games");
        clearCache("game");
        clearCache("latestGames");
    }

    @Scheduled(fixedRate = 3600000)
    public void refreshLatestGames(){

        clearCache("latestGames");

        gameApiService.getLatestGames();
    }

    private void clearCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);

        if (cache != null) {
            cache.clear();
        }
    }
}
