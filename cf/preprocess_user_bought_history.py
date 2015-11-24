import csv

with open("user_bought_history.txt") as f, open('user_history.csv', 'wb') as result:
    csv_writer = csv.writer(result)
    title = ["user_id", "item_id", "create_at"]
    csv_writer.writerow([x for x in title])

    for line in f:
        item = line.strip().split(" ")
        # item.append(1)
        csv_writer.writerow([x for x in item])
