#include <iostream>
#include <vector>
#include <random>
#include <array>

using namespace std;

std::vector<int> calcProductVector(const std::vector<int> &vec) {
    std::vector<int> result(vec.size());
    int p = 1;
    for (size_t i = 0; i < vec.size(); ++i) {
        result[i] = p;
        p *= vec[i];

    }
    p = 1;
    for (size_t i = vec.size(); i > 0; --i) {
        size_t j = i - 1;
        result[j] *= p;
        p *= vec[j];
    }
    return result;
}

/**
 * Very easy. Greedy algorithm, taking locally optimum choice.
 * O(n) runtime complexity. Loop once. My stock values change
 * over time. If stock current worth less, than  smallest known
 * price, then make that current worth the new minimum and make
 * your calculations based on that. That way you always have a
 * larger potential payout down the line.
 * @param v
 */
void getBestStock(const std::vector<int> &v) {
    int minprice = v[0];
    int maxProfit = v[1] - v[0];
    int potential;
    for (int i = 1; i < int(v.size()); ++i) {
        potential = v[i] - minprice;
        maxProfit = max(maxProfit, potential);
        minprice = min(minprice, v[i]);
    }
    cout << "Max profit: " << maxProfit << endl;
    cout << "Buy when price is: " << minprice << endl;
    cout << "Sell when price is: " << minprice + maxProfit << endl;

}

int main() {
    std::vector<int> vec = {10, 7, 5, 8, 11, 9};
    getBestStock(vec);

    return 0;
}
