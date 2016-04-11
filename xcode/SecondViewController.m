//
//  SecondViewController.m
//  GuessingGame_CAK
//
//  Created by Charles Kiefer (CAKIEF0938) on 2/9/15.
//  Copyright (c) 2015 Charles Kiefer (CAKIEF0938). All rights reserved.
//

#import "SecondViewController.h"

@interface SecondViewController ()

@end

@implementation SecondViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    
    if((bool)[settings integerForKey:@"losing"])
    {
        [_losingSwitch setOn:YES animated:YES];
    }
    else
    {
        [_losingSwitch setOn:NO animated:NO];
    }
    
    switch ([settings integerForKey:@"Difficulty"])
    {
        case 100:
        [_radioDifficulty setSelectedSegmentIndex:1];
            break;
        case 1000:
            [_radioDifficulty setSelectedSegmentIndex:2];
            break;
        case 1000000:
            [_radioDifficulty setSelectedSegmentIndex:3];
            break;
        default:
            [_radioDifficulty setSelectedSegmentIndex:0];
            break;
    }
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)indexChanged:(UISegmentedControl *)sender {
    
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    
    switch (self.radioDifficulty.selectedSegmentIndex)
    {
        case 0:
            [settings setInteger:10 forKey:@"Difficulty"];
            [settings synchronize];
            break;
        case 1:
            [settings setInteger:100 forKey:@"Difficulty"];
            [settings synchronize];
            break;
        case 2:
            [settings setInteger:1000 forKey:@"Difficulty"];
            [settings synchronize];
            break;
        case 3:
            [settings setInteger:1000000 forKey:@"Difficulty"];
            [settings synchronize];
            break;
        default:
            break; 
    }
}

- (IBAction)switchChanged:(UISwitch *)sender {
    
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    
    bool tempLosing;
    tempLosing = (bool)[settings integerForKey:@"losing"];
    
    if (tempLosing == YES)
    {
        [settings setBool:NO forKey:@"losing"];
        [settings synchronize];
    }
    
    else
    {
        [settings setBool:YES forKey:@"losing"];
        [settings synchronize];
    }
    
    
}
@end
