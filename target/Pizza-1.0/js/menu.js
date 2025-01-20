document.addEventListener('DOMContentLoaded', () => {
    const foodItems = document.querySelectorAll(".food-item");
    const totalPriceElement = document.getElementById("total-price");

    foodItems.forEach((item) => {
        const input = item.querySelector('.count-input');
        const price = parseFloat(item.getAttribute('data-price')); // Assume data-price is set on each food-item
        const increase = item.querySelector('.increase');
        const decrease = item.querySelector('.decrease');

        increase.addEventListener('click', () => {
            input.value = parseInt(input.value) + 1;
            updateHighlight(item, parseInt(input.value));
            updateTotal();
        });

        decrease.addEventListener('click', () => {
            input.value = Math.max(0, parseInt(input.value) - 1);
            updateHighlight(item, parseInt(input.value));
            updateTotal();
        });
    });

    // Function to highlight the food item based on counter value
    function updateHighlight(foodItem, value) {
        if (value > 0) {
            foodItem.classList.add("highlight");
        } else {
            foodItem.classList.remove("highlight");
        }
    }

    // Function to calculate the total price
    function updateTotal() {
        let total = 0;
        foodItems.forEach((item) => {
            const input = item.querySelector('.count-input');
            const price = parseFloat(item.getAttribute('data-price'));
            const count = parseInt(input.value);
            total += count * price;
        });
        
        totalPriceElement.value = total.toFixed(2); // Format to 2 decimal places
    }
});
