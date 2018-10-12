//
//  RequestManager.h
//  JSPathProject
//
//  Created by biostime on 2018/10/9.
//  Copyright © 2018 biostime. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface RequestManager : NSObject

+ (instancetype)manager;

- (void)get:(NSString *)method success:(void (^ __nullable)(id data))success fail:(void (^ __nullable)(id data))fail;



@end

NS_ASSUME_NONNULL_END
