//
//  RequestManager.m
//  JSPathProject
//
//  Created by biostime on 2018/10/9.
//  Copyright © 2018 biostime. All rights reserved.
//

#import "RequestManager.h"

@interface RequestManager ()

@property (nonatomic , copy)void (^success)(id data);
@property (nonatomic , copy)void (^fail)(id data);

@property (nonatomic , copy)NSMutableData *requestData;

@end

@implementation RequestManager

static RequestManager *_manager=nil;
+ (instancetype)manager
{
    return [[self alloc] init];
}

+(instancetype)allocWithZone:(struct _NSZone *)zone
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        if (_manager == nil) {
            _manager = [super allocWithZone:zone];
        }
    });
    return _manager;
}

-(id) copyWithZone:(struct _NSZone *)zone
{
    return _manager;
}

-(id)mutableCopyWithZone:(NSZone *)zone {
    
    return _manager;
}

- (void)get:(NSString *)method success:(void (^ __nullable)(id data))success fail:(void (^ __nullable)(id data))fail
{
    NSURL *url = [NSURL URLWithString:method];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    NSURLSession *session=[NSURLSession sharedSession];
    
    NSURLSessionDataTask *dataTask=[session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        if (!error) {
            NSError *error;
            NSDictionary *dict=[NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&error];
            if (error) {
                fail(error.userInfo);
            }
            else{
                success(dict);
            }
        }
        else{
            fail(error.userInfo);
        }
    }];
    [dataTask resume];
}



@end
