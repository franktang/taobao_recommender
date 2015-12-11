#!/usr/bin/env python
import sys

arr_test_items = []


def is_in_test_items(item):
    if item in arr_test_items:
        return True
    else:
        return False


with open("test_items.txt") as test_items:
    for line in test_items:
        test_item = line.strip()
        arr_test_items.append(test_item)

if __name__ == '__main__':
    for line in sys.stdin:
        line = line.strip()
        sim_info = line.split(",")

        if is_in_test_items(sim_info[0]) and not is_in_test_items(sim_info[1]):
            print('"%s" "%s" "%s"' % (sim_info[0], sim_info[1], sim_info[2]))
        elif not is_in_test_items(sim_info[0]) and is_in_test_items(sim_info[1]):
            temp = sim_info[0]
            sim_info[0] = sim_info[1]
            sim_info[1] = temp
            print('"%s" "%s" "%s"' % (sim_info[0], sim_info[1], sim_info[2]))