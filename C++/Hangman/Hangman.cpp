#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <algorithm>
#include <cctype>
#include <list>

using namespace std;

char asciitolower(char c);

int main()
{
    string line, strings[999];
    ifstream myfile;
    //getting the file
    myfile.open("C:/Users/nikla/Desktop/JavaExample/depend/list.txt");
    if (myfile.is_open())
    {
        int i = 0;
        //scanning the file
        while (getline(myfile, line))
        {
            
            transform(line.begin(), line.end(), line.begin(), asciitolower);
            strings[i] = line;
            i++;
        }
    }
    else
    {
        cout << "Unable to find the file!" << endl;
        cin;
    }

    //game loop
    int wrong = 0, max = 10;
    string guess, c = strings[(rand() % strings->length() + 0)];
    cout << c << endl;
    cout << "Start guessing!" << endl;
    while (true) 
    {
        if (wrong > max)
            break;
        cin >> guess;
        if (guess.length() > 1)
        {
            if (c == guess)
            {
                cout << "You guessed to word! The word was " << c << "." << endl;
                break;
            }
            else
            {
                bool constains = false;
                for (unsigned int i = 0; i < guess.length() - 1; i++)
                {
                    if (c[i] == guess[0])
                    {
                        constains = true;
                    }
                }
                if (constains)
                {
                    cout << "The character " << guess[0] << " is part of the word." << endl;
                }
                else
                {
                    wrong++;
                    cout << "The character " << guess[0] << " isn't part of the word. You have " << wrong << " wrong guess of " << max << " possible guesses." << endl;
                }
            }
        }
    }

    return 0;
}

char asciitolower(char in)
{
    if (in <= 'Z' && in >= 'A')
        return in - ('Z' - 'z');
    return in;
}
