#include <iostream>
#include <atomic>
#include <thread>
#include <vector>

std::atomic<bool> ready (false);
std::atomic_flag winner = ATOMIC_FLAG_INIT;

void count1m(int id) {
   while (!ready) { std::this_thread::yield(); }   // wait for the ready signal
   for (volatile int i = 0; i < 1e6; ++i) {}       // go!, count to 1 million
   if (!winner.test_and_set()) { std::cout << "thread #" << id << " won!\n"; }
}

void pause_thread(int n) {
   std::this_thread::sleep_for(std::chrono::seconds(n));
   std::cout << "pause of " << n << " seconds ended\n";
}

int main() {
   std::vector<std::thread> threads;
   std::cout << "spawning 10 threads that count to 1 million...\n";
   for (int i = 1; i <= 10; ++i) threads.push_back(std::thread(count1m, i));
   ready = true;
   for (auto &th : threads) th.join();

   std::cout << "=================\n";
   // std::thread::join example
   std::thread t1 (pause_thread, 1);
   std::thread t2 (pause_thread, 2);
   std::thread t3 (pause_thread, 3);
   std::cout << "Done spawning threads. Now waiting for them to join:\n";
   t1.join();
   t2.join();
   t3.join();
   std::cout << "All threads joined!\n";

   return 0;
}
