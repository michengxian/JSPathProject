//
//  OCBox.m
//  MVVM-test
//
//  Created by biostime on 2018/7/20.
//  Copyright © 2018年 biostime. All rights reserved.
//

#import "OCBox.h"

@implementation OCBox

- (id)unbox
{
    if (self.obj) return self.obj;
    return self;
}
- (void *)unboxPointer
{
    return self.pointer;
}
- (Class)unboxClass
{
    return self.cls;
}

+(instancetype)boxPointer:(void *)pointer
{
    OCBox *box=[[OCBox alloc]init];
    box.pointer=pointer;
    return box;
}

+(instancetype)boxClass:(Class)cls
{
    OCBox *box=[[OCBox alloc]init];
    box.cls=cls;
    return box;
}

+(instancetype)boxObj:(id)obj
{
    OCBox *box=[[OCBox alloc]init];
    box.obj=obj;
    return box;
}

+(instancetype)boxWeakObj:(id)obj
{
    OCBox *box=[[OCBox alloc]init];
    box.weakObj=obj;
    return box;
}

+(instancetype)boxAssignObj:(id)obj
{
    OCBox *box=[[OCBox alloc]init];
    box.assignObj=obj;
    return box;
}

@end
