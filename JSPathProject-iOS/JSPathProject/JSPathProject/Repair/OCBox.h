//
//  OCBox.h
//  MVVM-test
//
//  Created by biostime on 2018/7/20.
//  Copyright © 2018年 biostime. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface OCBox : NSObject

@property (nonatomic) id obj;
@property (nonatomic) void *pointer;
@property (nonatomic) Class cls;
@property (nonatomic, weak) id weakObj;
@property (nonatomic, assign) id assignObj;
- (id)unbox;
- (void *)unboxPointer;
- (Class)unboxClass;

+(instancetype)boxPointer:(void *)pointer;
+(instancetype)boxClass:(Class)cls;
+(instancetype)boxObj:(id)obj;
+(instancetype)boxWeakObj:(id)obj;
+(instancetype)boxAssignObj:(id)obj;

@end
