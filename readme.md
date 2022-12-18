Definitions:

Game: A single game, serves as a container around Match.

Match : A single match, may or may not consist of multiple rounds. this is set by user input.

Round : A single round, may or may not consist of multiple turns. this depends on
        if the last turn results in a draw, a stalemate, or there are multiple winners.
        
        If there is a single winner, the round is over.
        If all thrown choices are the same, it is considered a draw, and the round is restarted
            with the participating players.
        If there are multiple winners, then the losing players are eliminated from this 
            round, and a new round starts with the winning players.

        