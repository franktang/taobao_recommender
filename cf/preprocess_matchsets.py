import csv
import itertools

data = []
singular_match_set = []
item_ids = []
match_item_ids = []
ratings = []

with open("dim_fashion_matchsets.txt") as f, open("data.csv", "wb") as result:
    csv_writer = csv.writer(result)
    title = ["item", "match_item", "rating"]
    csv_writer.writerow([x for x in title])

    for line in f:
        line_item = line.strip().split(" ")

        match_sets = []
        match_sets_str = line_item[1].split(";")
        for match_set_str in match_sets_str:
            items = match_set_str.split(",")
            match_sets.append(items)

        for x in range(0, len(match_sets)):
            for y in range(x + 1, len(match_sets)):
                any_two_sets = []
                any_two_sets.append(match_sets[x])
                any_two_sets.append(match_sets[y])
                combinations = list(itertools.product(*any_two_sets))
                for combination in combinations:
                    list_combination = list(combination)
                    if list_combination not in singular_match_set:
                        list_combination.append(1)
                        csv_writer.writerow([x for x in list_combination])
