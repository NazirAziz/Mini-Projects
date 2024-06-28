#include <iostream>
#include <fstream>
#include <vector>

std::vector<std::string> LIST;


void get_data(){
    std::fstream read("list.txt");
    if (read.is_open()){
        while (!read.eof()){
            std::string temp;
            std::getline(read, temp);
            LIST.push_back(temp);
        }
    }else{
        std::cout << "No open";
    }
}

void write_data(){
    std::ofstream write ("list.txt", std::ios::trunc);
    for (int i = 0; i < LIST.size(); ++i){
        write << LIST[i];
        if (i != LIST.size()-1)
            write << std::endl;
    }
        
} 

void show_data(){
    int i = 1;
    for(std::string list : LIST)
        std::cout << i++ << ": " << list << std::endl;
}

void add_data(){
    std::cout << "Enter your TODO: ";
    std::string temp;
    std::getline(std::cin >> std::ws, temp);
    LIST.push_back(temp);

}

void remove_data(){
    show_data();
    std::cout << "What TODO to remove: ";
    int choice; std::cin >> choice;
    choice--;
    if (choice >= 0 && choice < LIST.size()){
        LIST.erase(LIST.begin() + choice);
    }
}
void Show(){
    show_data();
    std::cin.clear();
    std::cin.ignore();
    std::cin.get();
}

int main(){
    get_data();
    while(true){
        system("cls");
        std::cout << "*****My TODO List*****\n";
        std::cout << "1. Show list\n";
        std::cout << "2. Add to the list\n";
        std::cout << "3. Remove from the list\n";
        std::cout << "4. Exit\n";
        int choice; std::cin >> choice;

        switch(choice){
            case 1: Show();
            break;
            case 2:{
                add_data();
                Show();
            }
            break;
            case 3:{
                remove_data();
                Show();
            }
            break;
            case 4:{
                write_data();
                return 0;
            }
            default: std::cout << "Error!\n";
        }
    }


}
