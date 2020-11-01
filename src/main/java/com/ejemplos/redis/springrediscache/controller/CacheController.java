package com.ejemplos.redis.springrediscache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/caches")
public class CacheController {
	
	private CacheManager cacheManager;
	
	@Autowired
	public CacheController(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	@PatchMapping("/{name}")
	public void evictCache(@PathVariable String name) {
		this.cacheManager.getCache(name).clear();
	}

}
