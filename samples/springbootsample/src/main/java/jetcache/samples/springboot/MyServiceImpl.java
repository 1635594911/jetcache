/**
 * Created on 2018/8/11.
 */
package jetcache.samples.springboot;

import com.alibaba.fastjson.JSON;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Component
public class MyServiceImpl implements MyService {

  @CreateCache(name = "myServiceCache", expire = 60)
  private Cache<String, String> cache;

  @Autowired
  private UserService userService;

  @Override
  public void createCacheDemo() {
    cache.put("myKey", "myValue");
    String myValue = cache.get("myKey");
    System.out.println("get 'myKey' from cache:" + myValue);
  }

  @Override
  public void cachedDemo() {
    User user = userService.loadUser(1);
    System.out.println("cache user:" + JSON.toJSONString(user));
    User user2 = userService.loadUser(1);
    System.out.println("cache user2:" + JSON.toJSONString(user2));
    try {
      Thread.sleep(60000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
