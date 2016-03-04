//
//  SecondViewController.h
//  GuessingGame_CAK
//
//  Created by Charles Kiefer (CAKIEF0938) on 2/9/15.
//  Copyright (c) 2015 Charles Kiefer (CAKIEF0938). All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SecondViewController : UIViewController

@property (weak, nonatomic) IBOutlet UISegmentedControl *radioDifficulty;

- (IBAction)indexChanged:(UISegmentedControl *)sender;


@property (weak, nonatomic) IBOutlet UISwitch *losingSwitch;
- (IBAction)switchChanged:(UISwitch *)sender;

@end

