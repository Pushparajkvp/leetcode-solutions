/*

Design a phone directory that initially has maxNumbers empty slots that can store numbers. The directory should store numbers, check if a certain slot is empty or not, and empty a given slot.

Implement the PhoneDirectory class:

PhoneDirectory(int maxNumbers) Initializes the phone directory with the number of available slots maxNumbers.
int get() Provides a number that is not assigned to anyone. Returns -1 if no number is available.
bool check(int number) Returns true if the slot number is available and false otherwise.
void release(int number) Recycles or releases the slot number.
 

Example 1:

Input
["PhoneDirectory", "get", "get", "check", "get", "check", "release", "check"]
[[3], [], [], [2], [], [2], [2], [2]]
Output
[null, 0, 1, true, 2, false, null, true]

Explanation
PhoneDirectory phoneDirectory = new PhoneDirectory(3);
phoneDirectory.get();      // It can return any available phone number. Here we assume it returns 0.
phoneDirectory.get();      // Assume it returns 1.
phoneDirectory.check(2);   // The number 2 is available, so return true.
phoneDirectory.get();      // It returns 2, the only number that is left.
phoneDirectory.check(2);   // The number 2 is no longer available, so return false.
phoneDirectory.release(2); // Release number 2 back to the pool.
phoneDirectory.check(2);   // Number 2 is available again, return true.
 

Constraints:

1 <= maxNumbers <= 104
0 <= number < maxNumbers
At most 2 * 104 calls will be made to get, check, and release.
*/

class PhoneDirectory {
    
    int[] arr;
    boolean[] available;
    int front, end, len;
    
    public PhoneDirectory(int maxNumbers) {
        len = maxNumbers;
        arr = new int[len];
        available = new boolean[len];
        front = end = -1;
        
        for(int it=0; it<len; it++)
            offer(it);
    }
    
    private void offer(int num) {
        if(front == -1) {
            front = end = 0;
            arr[front] = num;
            available[num] = true;
        } else {
            end = (end + 1) % len;
            arr[end] = num;
            available[num] = true;
        }
    }
    
    public int get() {
        if(front != -1) {
            int ret = arr[front];
            available[ret] = false;
            if(front == end) {
                front = end = -1;
            } else {
                front = (front + 1) % len;
            }
            
            return ret;
        }
        return -1;
    }
    
    public boolean check(int number) {
        return available[number];
    }
    
    public void release(int number) {
        if(!available[number]) {
            offer(number);
        }
    }
}