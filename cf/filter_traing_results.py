import csv

item_cat = {}

arr_test_items = []


def is_item_same_category(item1_id, item2_id):
    item1_cat = item_cat[item1_id]
    item2_cat = item_cat[item2_id]
    if item1_cat == item2_cat:
        return True
    else:
        return False


def is_in_test_items(item):
    if item in arr_test_items:
        return True
    else:
        return False


with open("dim_items.txt") as items, open("similar_itmes_traing_result.csv") as traing_result, open("filter_result.csv",
                                                                                                    "wb") as filter_result, open(
    "test_items.txt") as test_items:
    for line in items:
        item_info = line.strip().split(" ")
        item_cat[item_info[0]] = item_info[1]
        # print(item_cat[item_info[0]])

    for line in test_items:
        test_item = line.strip()
        arr_test_items.append(test_item)

    csv_writer = csv.writer(filter_result)
    title = ["item_id", "similar", "score", "rank"]
    csv_writer.writerow([x for x in title])

    next(traing_result)
    for line in traing_result:
        sim_info = line.strip().split(",")
        # print(sim_info)
        if is_item_same_category(sim_info[0], sim_info[1]) and (
            is_in_test_items(sim_info[0]) or is_in_test_items(sim_info[1])):
            csv_writer.writerow([x for x in sim_info])
