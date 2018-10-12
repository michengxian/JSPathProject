# JSPathProject
>`JSPath`是`iOS`端强大的热更新工具，但是不幸的是，被苹果爸爸禁用了。导致现在的应用出了bug只能老老实实的发布新版本，不能通过热更新来实时修复。给我们带来了很多的不便。

## JSPath

有时候一点点小问题，也需要发布新版本，虽然现在苹果审核速度快了很多，一般两到三天就能审核通过，但碰到一些紧急问题，难免还是有点不方便。

所以，我就又开始想着怎么规避审核来集成`JSPath`，于是有了下面的思路

***

>以下是`iOS`端的工程**目录**：

![工程目录](https://upload-images.jianshu.io/upload_images/1621313-711f73e39b56bd3a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

1. `Repair`文件夹是把`JSPath`中`JPEngine`类进行了拆分与改名。避免苹果审核查出JSPath相关字眼。

2. 在`ViewController`中创建了一个`Button`，`Button`的点击事件如下：

```
- (IBAction)storyboardTestCarshAction:(id)sender {
    NSLog(@"--------testWillCrash--------");
    
    NSString *crash = [self testCrash];
    
    NSLog(@"--------testDidCrash--------%@",crash);
}

-(NSString *)testCrash
{
    NSArray *array = [[NSArray alloc]initWithObjects:@"1",@"2", nil];
    return array[2];
}
```

很明显，如果这样的代码上线，用户一点击按钮程序就会崩溃，因为数组`array`中只有两个元素"1"、"2"，当你去取第三个元素的时候就会崩溃（不多说）。

3. 这时如果我们在`AppDelegate`中做以下处理，就能避免这个问题。

```
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    
    [[RequestManager manager] get:@"http://localhost:8080/mobile/JSPath/repair" success:^(id  _Nonnull data) {
        NSDictionary *response=[data objectForKey:@"response"];
        NSString *repair=[response objectForKey:@"repair"];
        NSString *base=[response objectForKey:@"base"];
        [[NSUserDefaults standardUserDefaults] setObject:base forKey:@"Repair"];
        
        [Repair startEngineWithBaseString:base];
        [Repair evaluateScript:repair];
        
    } fail:^(id  _Nonnull data) {
        NSLog(@"------------接口请求错误-------------");
    }];
    
    return YES;
}
```

***

>说完iOS端，现在我们来说说java后台

首先是目录：
![java端目录](https://upload-images.jianshu.io/upload_images/1621313-6a9f7b6b0f260d95.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
其他文件不多说，我们来看下图片中标记出来的两个文件：
1. `demo.js`是我们写的修改代码

```
defineClass('ViewController : UIViewController',[], {
    viewWillAppear:function(animated){
        self.super().viewWillAppear(animated);
        self.view().setBackgroundColor(require('UIColor').grayColor());
    },
   testCrash:function () {
        var  array=['0','1'];
        return array[1];
    },
    }
)
```

2. `JSPatch.js`文件是原JSPath中的文件，直接挪过来的。（没有任何修改）
3. 我们在看`/src/main/java/com/mobil/JSPath/repository/JSPathRepositoryImpl`文件

```
package com.mobile.JSPath.repository;

import com.mobile.JSPath.bean.JSPathBean;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class JSPathRepositoryImpl implements JSPathRepository {
    @Override
    public JSPathBean repair() {
        JSPathBean bean = new JSPathBean();

        String path = JSPathRepositoryImpl.class.getResource("/").getPath();
        File basePath = new File(path + "JSPatch.js");
        String base = "";
        if (basePath.exists()){
            try {
                FileReader fr = new FileReader(basePath);
                InputStream is=new FileInputStream(basePath);
                int size=is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    base=base+c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File repairPath = new File(path + "demo.js");
        String repair = "";
        if (repairPath.exists()){
            try {
                FileReader fr = new FileReader(repairPath);
                InputStream is=new FileInputStream(repairPath);
                int size=is.available();
                char[] a = new char[size];
                fr.read(a);
                for (char c : a) {
                    repair=repair+c;
                }
                is.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bean.setBase(base);
        bean.setRepair(repair);
        return bean;
    }
}
```
如果考虑安全情况，可以在返回的字符串中自行加密，`iOS`端也需要进行相应的解密


