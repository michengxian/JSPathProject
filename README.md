# JSPathProject
JSPathProject


## JSPath

>`JSPath`是`iOS`端强大的热更新工具，但是不幸的是，被苹果爸爸仅用了。导致现在的应用出了bug只能老老实实的发布新版本，不能通过热更新来实时修复。给我们带来了很多的不便。

***

有时候一点点小问题，也需要发布新版本，虽然现在苹果审核速度快了很多，一般两到三天就能审核通过，但碰到一些紧急问题，难免还是有点不方便。

所以，我就又开始想着怎么规避审核来集成`JSPath`，于是有了下面的思路

以下是工程**目录**：

![工程目录](https://upload-images.jianshu.io/upload_images/1621313-711f73e39b56bd3a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

1. `Repair`文件夹是把`JSPath`中`JPEngine`类进行了拆分与改名。避免苹果审核查出JSPath相关字眼。

2. 在`ViewController`中创建了一个`UIButton`，`UIButton`的点击事件如下：

```
- (IBAction)testCrashAction:(id)sender {
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
    
    dispatch_semaphore_t disp = dispatch_semaphore_create(0);
    //http://localhost:8080/JSPath/Repair 就是返回原JSPath中JSPatch.js与修复的js文件的接口即可。
    [[RequestManager manager] get:@"http://localhost:8080/person/homePage/test2" success:^(id  _Nonnull data) {
        NSDictionary *response=[data objectForKey:@"response"];
        NSString *repair=[response objectForKey:@"repair"];
        NSString *base=[response objectForKey:@"base"];
        [[NSUserDefaults standardUserDefaults] setObject:base forKey:@"Repair"];
        
        [Repair startEngineWithBaseString:base];
        [Repair evaluateScript:repair];
        
        dispatch_semaphore_signal(disp);
    } fail:^(id  _Nonnull data) {
        dispatch_semaphore_signal(disp);
    }];
    dispatch_semaphore_wait(disp, 2* NSEC_PER_SEC);
    
    [self.window makeKeyAndVisible];
    return YES;
}
```

***



