/**
 * savings-goal.js
 * Handles operations related to savings goals: creating, viewing, and updating.
 */

// ----------------------------------------------------
// 1. Create Savings Goal Logic (for savings-goal.html)
// ----------------------------------------------------
function initCreateSavingsGoal() {
    const savingsGoalForm = document.getElementById('savingsGoalForm');
    if (savingsGoalForm) {
        savingsGoalForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const goalName = document.getElementById('goalName').value;
            const targetAmount = parseFloat(document.getElementById('targetAmount').value);
            const currentAmount = parseFloat(document.getElementById('currentAmount').value);

            const payload = {
                goalName: goalName,
                targetAmount: targetAmount,
                currentAmount: currentAmount
            };

            const submitBtn = document.getElementById('submitBtn');
            if (submitBtn) submitBtn.disabled = true;

            fetch('/api/savings-goals', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'savings-goal-view.html';
                } else {
                    alert('Failed to save savings goal. Please try again.');
                    if (submitBtn) submitBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error saving savings goal:', error);
                alert('An error occurred while saving savings goal.');
                if (submitBtn) submitBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// 2. View and Update Savings Goal (for savings-goal-view.html)
// ----------------------------------------------------
function loadSavingsGoal() {
    fetch('/api/savings-goals/latest')
        .then(response => {
            if (response.status === 204 || !response.ok) {
                return null;
            }
            return response.json();
        })
        .then(goal => {
            const goalSec = document.getElementById('goalSection');
            const noGoalSec = document.getElementById('noGoalSection');

            if (goal && goal.goalName) {
                if (goalSec) goalSec.style.display = 'block';
                if (noGoalSec) noGoalSec.style.display = 'none';

                const idElem = document.getElementById('goalId');
                const nameElem = document.getElementById('goalName');
                const nameDisp = document.getElementById('goalNameDisplay');
                const targetDisp = document.getElementById('targetAmountDisplay');
                const currentDisp = document.getElementById('currentAmountDisplay');
                const remDisp = document.getElementById('remainingAmountDisplay');
                const barElem = document.getElementById('progressBar');
                const progressTextElem = document.getElementById('progressText');
                const updateTargetElem = document.getElementById('updateTargetAmount');
                const updateCurrentElem = document.getElementById('updateCurrentAmount');

                if (idElem) idElem.value = goal.goalId;
                if (nameElem) nameElem.value = goal.goalName;
                if (nameDisp) nameDisp.innerText = '🎯 ' + goal.goalName;
                if (targetDisp) targetDisp.innerHTML = '&#8377;' + Number(goal.targetAmount || 0).toFixed(2);
                if (currentDisp) currentDisp.innerHTML = '&#8377;' + Number(goal.currentAmount || 0).toFixed(2);
                if (remDisp) remDisp.innerHTML = '&#8377;' + Number(goal.remaining || 0).toFixed(2);

                const progress = Number(goal.progress || 0).toFixed(2);
                if (barElem) barElem.style.width = progress + '%';
                if (progressTextElem) progressTextElem.innerText = progress + '% completed';

                if (updateTargetElem) updateTargetElem.value = goal.targetAmount;
                if (updateCurrentElem) updateCurrentElem.value = goal.currentAmount;
            } else {
                if (goalSec) goalSec.style.display = 'none';
                if (noGoalSec) noGoalSec.style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error fetching savings goal:', error);
            const noGoalSec = document.getElementById('noGoalSection');
            if (noGoalSec) noGoalSec.style.display = 'block';
        });
}

function initSavingsGoalView() {
    loadSavingsGoal();

    const updateForm = document.getElementById('updateGoalForm');
    if (updateForm) {
        updateForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const goalId = parseInt(document.getElementById('goalId').value, 10);
            const goalName = document.getElementById('goalName').value;
            const targetAmount = parseFloat(document.getElementById('updateTargetAmount').value);
            const currentAmount = parseFloat(document.getElementById('updateCurrentAmount').value);

            const payload = {
                goalId: goalId,
                goalName: goalName,
                targetAmount: targetAmount,
                currentAmount: currentAmount
            };

            const updateBtn = document.getElementById('updateBtn');
            if (updateBtn) updateBtn.disabled = true;

            fetch('/api/savings-goals', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })
            .then(response => {
                if (response.ok) {
                    loadSavingsGoal();
                } else {
                    alert('Failed to update savings goal.');
                }
                if (updateBtn) updateBtn.disabled = false;
            })
            .catch(error => {
                console.error('Error updating savings goal:', error);
                alert('An error occurred while updating savings goal.');
                if (updateBtn) updateBtn.disabled = false;
            });
        });
    }
}

// ----------------------------------------------------
// Auto-detect page on DOMContentLoaded
// ----------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('savingsGoalForm')) {
        initCreateSavingsGoal();
    } else if (document.getElementById('goalSection')) {
        initSavingsGoalView();
    }
});
