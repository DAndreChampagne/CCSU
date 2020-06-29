import operator
from enum import Enum
from random import shuffle


####################################################################
# question 1
# Knapsack problem
####################################################################

class Pair:
    def __init__(self, label, weight, value):
        self.label = label
        self.weight = weight
        self.value = value

    def __str__(self):
        return f"({self.label},{self.weight},{self.value})"


def knapsack_fractional(items, capacity):
    items.sort(key=operator.attrgetter('value'), reverse=True)
    value = 0

    for item in items:
        if capacity == 0:
            break
        elif capacity - item.weight >= 0:
            print(f"taking all of {item}")
            capacity -= item.weight
            value += item.value
        else:
            fraction = capacity / item.weight
            print(f"taking {fraction} of {item}")

            value += fraction * item.value
            capacity -= fraction
            break  # for loop. If we're at the point of taking a fraction, we're done.

    return value


# objects = [
#     Pair("A", 2, 6),
#     Pair("B", 3, 9),
#     Pair("C", 2, 10),
#     Pair("D", 4, 8)
# ]
# print(*objects)
# print(knapsack_fractional(objects, 5))

objects = [
    Pair("A", 8, 5),
    Pair("B", 5, 10),
    Pair("C", 10, 12),
    Pair("D", 9, 4),
    Pair("E", 10, 20),
    Pair("F", 4, 16)
]
print(*objects)
print(knapsack_fractional(objects, 25))


####################################################################
# question 4
# card guessing
####################################################################

# class CardType(Enum):
#     Spades = 1
#     Hearts = 2
#     Clubs = 3
#     Diamonds = 4
#
#     def __str__(self):
#         return self.name
#
#
# class Card:
#     def __init__(self, t: CardType, number: int):
#         self.type = t
#         self.number = number
#
#     def __str__(self):
#         return f"({self.number} of {self.type})"
#
#
# cards = [
#     Card(CardType.Spades, 1),
#
#     Card(CardType.Spades, 2),
#     Card(CardType.Spades, 2),
#
#     Card(CardType.Spades, 3),
#     Card(CardType.Spades, 3),
#     Card(CardType.Spades, 3),
#
#     Card(CardType.Spades, 4),
#     Card(CardType.Spades, 4),
#     Card(CardType.Spades, 4),
#     Card(CardType.Spades, 4),
#
#     Card(CardType.Spades, 5),
#     Card(CardType.Spades, 5),
#     Card(CardType.Spades, 5),
#     Card(CardType.Spades, 5),
#     Card(CardType.Spades, 5),
#
# ]
#
# shuffle(cards)
# goal = cards[0]
# guessSuit = None
# guessNumber = None
#
# print(f"We are trying to guess the {goal}")
# print("Answers all questions with 'yes' or 'no'!")
#
# print("1. Is your card either a diamond or heart?")
# x = input()
#
# if x == 'yes':
#     print('2. Is your card a diamond?')
#     x = input()
#     guessSuit = CardType.Diamonds if x == 'yes' else CardType.Hearts
#
# else:
#     print('2. Is your card a spade?')
#     x = input()
#     guessSuit = CardType.Spades if x == 'yes' else CardType.Clubs
#
#
# print("3. Is your card either a 3?")
# x = input()
#
# if x == 'yes':
#     guessNumber = 3
# else:
#     print('4. Is your card a 1 or a 2?')
#     x = input()
#     if x == 'yes':
#         print('5. Is your card a 1?')
#         x = input()
#         guessNumber = 1 if x == 'yes' else 2
#     else:
#         print('5. Is your card a 4?')
#         x = input()
#         guessNumber = 4 if x == 'yes' else 5
#
# print(f"We think you have the {guessNumber} of {guessSuit.name}")
