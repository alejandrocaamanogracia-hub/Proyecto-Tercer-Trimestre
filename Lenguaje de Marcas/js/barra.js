        document.querySelectorAll('.sidebar__link').forEach(link => {
            link.addEventListener('click', function (e) {
                // Ripple
                const ripple = document.createElement('span');
                const rect = this.getBoundingClientRect();
                const size = Math.max(rect.width, rect.height);
                ripple.classList.add('ripple');
                ripple.style.cssText = `
                    width: ${size}px; height: ${size}px;
                    left: ${e.clientX - rect.left - size / 2}px;
                    top: ${e.clientY - rect.top - size / 2}px;
                `;
                this.appendChild(ripple);
                setTimeout(() => ripple.remove(), 500);
 
                // Active state
                document.querySelectorAll('.sidebar__link').forEach(l => l.classList.remove('sidebar__link--active'));
                this.classList.add('sidebar__link--active');
            });
        });
