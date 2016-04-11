//
//  FirstViewController.h
//  GuessingGame_CAK
//
//  Created by Charles Kiefer (CAKIEF0938) on 2/9/15.
//  Copyright (c) 2015 Charles Kiefer (CAKIEF0938). All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FirstViewController : UIViewController

@property (weak, nonatomic) IBOutlet UILabel *lblPrompt;
@property (weak, nonatomic) IBOutlet UILabel *lblGuessRange;
@property (weak, nonatomic) IBOutlet UITextField *txtGuessField;
@property (weak, nonatomic) IBOutlet UIButton *btnGuess;
@property (weak, nonatomic) IBOutlet UIButton *btnPlayAgain;
- (IBAction)guess:(id)sender;
- (IBAction)playAgain:(id)sender;
@property (weak, nonatomic) IBOutlet UILabel *lblLossCount;

@end

