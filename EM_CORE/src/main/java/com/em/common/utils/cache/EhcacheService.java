package com.em.common.utils.cache;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheService implements ICacheService<CacheManager> {
	// 日志记录器
	public final Logger logger = Logger.getLogger(this.getClass());
	private static CacheManager cacheManager;

	@Override
	public void setCacheManager(CacheManager manager) {
		this.cacheManager = manager;
	}

	@Override
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	private Object get(String key) {
		return getWhithCacheName(DEFAULT_CACHE, key);
	}

	@Override
	public String getString(String key) {
		Object o = get(key);
		if (o != null) {
			return o.toString();
		}
		return null;
	}

	@Override
	public byte[] getByteArray(String key) {
		Object o = get(key);
		if (o != null) {
			return (byte[]) o;
		}
		return null;
	}

	@Override
	public Map getMap(String key) {
		Object o = get(key);
		if (o != null) {
			return (Map) o;
		}
		return null;
	}

	@Override
	public Set getSet(String key) {
		Object o = get(key);
		if (o != null) {
			return (Set) o;
		}
		return null;
	}

	@Override
	public List getList(String key) {
		Object o = get(key);
		if (o != null) {
			return (List) o;
		}
		return null;
	}

	@Override
	public String putString(String key, String value) {
		if (value == null) {
			return null;
		}
		putWhithCacheName(DEFAULT_CACHE, key, value);
		return null;
	}

	@Override
	public String putExpireString(String key, String value, int seconds) {
		if (value == null) {
			return null;
		}
		putWhithCacheNameAndExpire(DEFAULT_CACHE, key, value, seconds);
		return null;
	}

	@Override
	public String putByteArray(String key, byte[] value) {
		if (value == null) {
			return null;
		}
		putWhithCacheName(DEFAULT_CACHE, key, value);
		return null;
	}

	@Override
	public String putExpireByteArray(String key, byte[] value, int seconds) {
		if (value == null) {
			return null;
		}
		putWhithCacheNameAndExpire(DEFAULT_CACHE, key, value, seconds);
		return null;
	}

	@Override
	public long putList(String key, List value) {
		if (value == null) {
			return 0;
		}
		putWhithCacheName(DEFAULT_CACHE, key, value);
		return 0;
	}

	@Override
	public long putExpireList(String key, List value, int seconds) {
		if (value == null) {
			return 0;
		}
		putWhithCacheNameAndExpire(DEFAULT_CACHE, key, value, seconds);
		return 0;
	}

	@Override
	public String putExpireMap(String key, Map value, int seconds) {
		if (value == null) {
			return null;
		}
		putWhithCacheNameAndExpire(DEFAULT_CACHE, key, value, seconds);
		return null;
	}

	@Override
	public String putMap(String key, Map value) {
		if (value == null) {
			return null;
		}
		putWhithCacheName(DEFAULT_CACHE, key, value);
		return null;
	}

	@Override
	public long putSet(String key, String... value) {
		if (value == null) {
			return 0;
		}
		putWhithCacheName(DEFAULT_CACHE, key, value);
		return 0;
	}

	/**
	 * 
	 * @Title: expire
	 * @author：刘宇祥
	 * @date：2017年1月12日下午1:51:47
	 * @Description: 设置过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	@Override
	public long expire(String key, int seconds) {

		Element element = getCache(DEFAULT_CACHE).get(key);
		element.setEternal(false);
		element.setTimeToLive(1);
		return 0;
	}

	@Override
	public void remove(String key) {
		removeWhithCacheName(DEFAULT_CACHE, key);

	}

	@Override
	public void removeByReg(String key) {
		removeByRegWhithCacheName(DEFAULT_CACHE, key);
	}

	public Object getWhithCacheName(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	public void putWhithCacheName(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	public void putWhithCacheNameAndExpire(String cacheName, String key, Object value, int timeToLiveSeconds) {
		Element element = new Element(key, value);
		element.setEternal(false);
		element.setTimeToLive(timeToLiveSeconds);
		getCache(cacheName).put(element);
	}

	public void removeWhithCacheName(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	public void removeByRegWhithCacheName(String cacheName, String key) {
		Set<String> set = getAllKeys(key);
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String temp = it.next();
			getCache(cacheName).remove(temp);
		}

	}

	/**
	 * 获得一个Cache，没有则创建一个。
	 * 
	 * @param cacheName
	 * @return
	 */
	private Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	@Override
	public String getStringValueFromMap(String key, String mapKey) {
		Map m = getMap(key);
		if (m == null) {
			return null;
		}
		Object obj = m.get(mapKey);
		if (obj == null) {
			return null;
		} else {
			return obj.toString();
		}
	}

	/**
	 * 
	 * @Title: getValueFromMap
	 * @author：刘宇祥
	 * @date：2017年1月12日下午2:10:28
	 * @Description: 非原子操作，不支持并发
	 * @param key
	 * @param mapKey
	 * @return
	 */
	@Override
	@Deprecated
	public String putStringValueOfMap(String key, String mapKey, String value) {
		Map m = getMap(key);
		if (m == null) {
			return null;
		}
		m.put(mapKey, value);
		putMap(key, m);
		return null;
	}

	/**
	 * 
	 * @Title: getValueFromMap
	 * @author：刘宇祥
	 * @date：2017年1月12日下午2:10:28
	 * @Description: 非原子操作，不支持并发
	 * @param key
	 * @param mapKey
	 * @return
	 */
	@Override
	@Deprecated
	public long removeKeyOfMap(String key, String mapKey) {
		Map m = getMap(key);
		if (m == null) {
			return 0;
		}
		m.remove(mapKey);
		putMap(key, m);
		return 0;
	}

	// FIXME DO NOTHING NOW
	@Override
	public void MapIncreBy(String key, String mapKey, long value) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @Title: getAllKeys
	 * @author：liuyx
	 * @date：2017年2月16日上午11:11:34
	 * @Description: TODO
	 * @param pattern
	 * @return
	 */
	@Override
	public Set getAllKeys(String pattern) {
		List list = getCache(DEFAULT_CACHE).getKeys();
		Set set = new HashSet();
		for (Object obj : list) {
			if (obj.toString().matches(pattern)) {
				set.add(obj);
			}
		}
		return set;
	}

	// FIXME DO NOTHING NOW
	@Override
	public void StringIncreBy(String key, long value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isKeyExist(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValueTypeByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@PreDestroy
	public void dostory() {
		if (cacheManager != null) {
			cacheManager.shutdown();
			logger.info(this.getClass().getName() + ":cacheManager has been shutdown");
		}
	}
}
