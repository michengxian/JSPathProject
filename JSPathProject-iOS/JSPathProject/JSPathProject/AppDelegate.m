//
//  AppDelegate.m
//  JSPathProject
//
//  Created by biostime on 2018/10/9.
//  Copyright © 2018 biostime. All rights reserved.
//

#import "AppDelegate.h"
#import "Repair.h"
#import "RequestManager.h"
#import "ViewController.h"
//#import "Repair.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    
    dispatch_semaphore_t disp = dispatch_semaphore_create(0);

    [[RequestManager manager] get:@"http://localhost:8080/person/homePage/test2" success:^(id  _Nonnull data) {
        NSDictionary *response=[data objectForKey:@"response"];
        NSString *repair=[response objectForKey:@"repair"];
        NSString *base=[response objectForKey:@"base"];
        
        [[NSUserDefaults standardUserDefaults] setObject:base forKey:@"Repair"];
        
        [Repair startEngineWithBaseString:base];

        [Repair evaluateScript:repair];

        
//        [Repair fix];
//        [Repair evalString:repair];
        
        dispatch_semaphore_signal(disp);


    } fail:^(id  _Nonnull data) {
        dispatch_semaphore_signal(disp);
    }];

    dispatch_semaphore_wait(disp, 3* NSEC_PER_SEC);
    
//    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    
//    ViewController *webVC=[[ViewController alloc]init];
//
//    self.window.rootViewController=webVC;
//
//    self.window.backgroundColor=[UIColor whiteColor];
    [self.window makeKeyAndVisible];
    
    
    return YES;
}


- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
}


- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}


- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
}


- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}


- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


@end
