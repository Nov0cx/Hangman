#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <algorithm>
#include <vector>
#include <ctime>

using namespace std;

char asciitolower(char c);

int main() {
    string line;
    vector<string> strings;
    ifstream myfile;
    string path = "C:/Users/nikla/Desktop/JavaExample/depend/list.txt", input;
    cout << "Write the path to the list and after that next. Example: C:/Users/nikla/Desktop/JavaExample/depend/list.txt" << endl;
    while (getline(cin, input)) {
        if (input == "next")
            break;
        path = input;
        cout << "You set the path to: " << path << endl;
    }

    //getting the file
    myfile.open(path);
    if (myfile.is_open()) {
        int i = 0;
        //scanning the file
        while (getline(myfile, line)) {

            transform(line.begin(), line.end(), line.begin(), asciitolower);
            strings.push_back(line);
            i++;
        }
    } else {
        cout << "Unable to find the file!" << endl;
        system("pause");
    }

    srand(static_cast<unsigned int>(time(nullptr)));

    int wrong = 0, max = 10;
    int r = rand() % (strings.size() - 0 + 1) + 0;
    string guess, c = strings[r];
    cout << "Start guessing!" << endl;
    cout << "The word is " << c.length() << " characters long." << endl;
    string pattern;
    int i = 0;
    vector<char> guessed = {};
    vector<string> allGuessed = {};

    for (int k = 0; k < c.length(); k++) {
        pattern.append("_");
    }

    cout << pattern << endl;

    //game loop
    while (true) {
            cin >> guess;
        if (find(allGuessed.begin(), allGuessed.end(), guess) != allGuessed.end()) {
            cout << "You already guessed that character/string!" << endl;
            continue;
        }
        if (c == guess && guess.length() > 1) {
            cout << "You guessed to word! The word was " << c << "." << endl;
            break;
        } else {
            bool b = false;
            for (char i : c) {
                if (i == guess[0]) {
                    b = true;
                }
            }
            if (b) {
                cout << "The character " << guess[0] << " is part of the word." << endl;
                guessed.push_back(guess[0]);
                pattern = "";
                stringstream ss;
                for (char car : c) {
                    if (car == guess[0] || find(guessed.begin(), guessed.end(), car) != guessed.end()) {
                        ss << car;
                    } else ss << "_";
                }
                pattern = ss.str();
                if (pattern == c) {
                    cout << "You won! You had " << wrong << " wrong guesses out of " << max << " max possible wrong guesses! The word was " << c << "." << endl;
                    break;
                }
                cout << pattern << endl;
            } else {
                wrong++;
                cout << "The character " << guess[0] << " isn't part of the word. You have " << wrong
                     << " wrong guess of " << max << " possible guesses." << endl;
            }
        }
        allGuessed.push_back(guess);
        if (wrong > max - 1) {
            cout << "You are out of guesses." << " The word was " << c << "." << endl;
            break;
        }
    }
    system("pause");
    return 0;
}

char asciitolower(char in) {
    if (in <= 'Z' && in >= 'A')
        return in - ('Z' - 'z');
    return in;
}