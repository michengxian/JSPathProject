//
//  ViewController.m
//  JSPathProject
//
//  Created by biostime on 2018/10/9.
//  Copyright © 2018 biostime. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    
}
- (IBAction)testCrashAction:(id)sender {
    NSLog(@"--------testWillCrash--------");
    
    NSString *crash = [self testCrash];
    
    NSLog(@"--------testDidCrash--------%@",crash);
}

-(NSString *)testCrash
{
    NSArray *array = [[NSArray alloc]initWithObjects:@"1",@"2", nil];
    
    return array[2];
//    NSLog(@"--------crash--------\n%@",array[2]);
}

@end
