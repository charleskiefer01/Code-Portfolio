//
//  FirstViewController.m
//  GuessingGame_CAK
//
//  Created by Charles Kiefer (CAKIEF0938) on 2/9/15.
//  Copyright (c) 2015 Charles Kiefer (CAKIEF0938). All rights reserved.
//

#import "FirstViewController.h"

typedef enum {
    INVALID = 0,
    EASY = 10,
    MEDIUM = 100,
    HARD = 1000,
    CRAZY = 1000000
} Difficulty;

@interface FirstViewController ()
{
   // diff tempDiff = getDifficulty();
    int theNumber;
    int maxRange;
    int guessesMax;
    int guessesUsed;
    bool losingEnabled;
}

@end

@implementation FirstViewController

@synthesize btnGuess;
@synthesize lblPrompt;
@synthesize txtGuessField;
@synthesize btnPlayAgain;
@synthesize lblGuessRange;
@synthesize lblLossCount;

- (void)viewDidLoad
{
    [super viewDidLoad];

    [self startNewGame:[self getDifficulty]];
    
    [lblPrompt setText:@""];
    
    [lblLossCount setText:@""];
}



- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (Difficulty) getDifficulty
{
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    Difficulty diff = (Difficulty)[settings integerForKey:@"Difficulty"];
    
    if (diff == INVALID)
    {
        [settings setInteger:10 forKey:@"Difficulty"];
        [settings synchronize];
        return [self getDifficulty];
    }
    
    return diff;
}

- (bool) getLosing
{
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    bool losing = (bool)[settings integerForKey:@"losing"];
    
    return losing;
}

- (int) getLosses
{
     NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    
    int losses = (int)[settings integerForKey:@"losses"];
    
    return losses;
}

- (IBAction)guess:(id)sender
{
    [lblPrompt setHidden:NO];
    if([txtGuessField.text isEqualToString:@""])
    {
        [lblPrompt setText:@"You can't guess nothing!"];
    }
    
    else
    {
        int guess = txtGuessField.text.intValue;
        [txtGuessField setText:@""];
        
        if(guess < 0)
        {
            [lblPrompt setText:@"You can't guess below 1!"];
        }
        else if(guess > maxRange)
        {
            [lblPrompt setText:[NSString stringWithFormat:@"You can't guess above %i!", maxRange]];
        }
        else
        {
            guessesUsed++;
            if(guess < theNumber)
            {
                [lblPrompt setText:[NSString stringWithFormat:@"%i was too low! %i guesses left.", guess, guessesMax - guessesUsed]];
            }
            else if(guess > theNumber)
            {
                [lblPrompt setText:[NSString stringWithFormat:@"%i was too high! %i guesses left.", guess, guessesMax - guessesUsed]];
            }
            
            else
            {
                [self endGame:YES Guesses:guessesUsed];
                return;
            }
            if (guessesMax - guessesUsed <= 0 && losingEnabled)
            {
                [self endGame:NO Guesses:guessesUsed];
            }
        }
    }
}

-(void)endGame:(BOOL)won Guesses:(int)guesses
{
    NSUserDefaults* settings = [NSUserDefaults standardUserDefaults];
    
    int tempLoss;
    tempLoss = [self getLosses];
    
    btnGuess.enabled = NO;
    txtGuessField.enabled = NO;
    NSString* outputText;
    if(won && losingEnabled)
    {
        if (guesses == 1)
        {
            outputText = @"You won in 1 guess!";
        }
        else if (guesses == guessesMax)
        {
            outputText = [NSString stringWithFormat:@"You won using all %i guesses!", guessesMax];
        }
        else if (guesses == guessesMax - 1)
        {
            outputText = [NSString stringWithFormat:@"You won with 1 guess left!"];
        }
        else
        {
            outputText = [NSString stringWithFormat:@"You won with %i guesses left!", guessesMax-guesses];
        }
    }
    else if (won && !losingEnabled)
    {
        outputText = [NSString stringWithFormat:@"You won. Good job."];
    }
    else
    {
        outputText = @"You lost.";
        tempLoss++;
        [settings setInteger:tempLoss forKey:@"losses"];
        
        [lblLossCount setText:[NSString stringWithFormat:@"Total losses: %i", tempLoss]];
    }
    
    [lblPrompt setText:outputText];
    [btnPlayAgain setHidden:NO];
}

- (IBAction)playAgain:(id)sender
{
    [self startNewGame:[self getDifficulty]];
}

- (void) startNewGame:(Difficulty) diff
{
    maxRange = (int)diff;
    
    losingEnabled = [self getLosing];
    
    [lblGuessRange setText:[NSString stringWithFormat:@"Guess a number between 1 and %i", maxRange]];
    
    guessesUsed = 0;
    
    theNumber = arc4random_uniform(maxRange)+1;
    
    guessesMax = [self guessesForDifficulty:diff];
    
    //[self showOutput:NO];
    
    btnGuess.enabled = YES;
    txtGuessField.enabled = YES;
    [lblPrompt setHidden:YES];
    
    [btnPlayAgain setHidden:YES];
    //    [set showOutput:NO];
    [txtGuessField becomeFirstResponder];
    
}


- (int)guessesForDifficulty:(Difficulty)diff
{
    switch(diff)
    {
        case EASY:
            return 4;
        case MEDIUM:
            return 7;
        case HARD:
            return 10;
        case CRAZY:
            return 20;
        default:
            return 4;
    }
}

@end
