"use client";

import Link from "next/link";

export default function DashboardNavbar() {

    const handleLogout = () => {

        localStorage.removeItem("token");

        window.location.href = "/login";
    };

    return (

        <nav className="w-full bg-black text-white px-8 py-4 flex justify-between items-center">

            <h1 className="text-2xl font-bold">
                TraCro
            </h1>

            <div className="flex gap-6 items-center">

                <Link href="/dashboard">
                    Dashboard
                </Link>

                <Link href="/workouts">
                    Workouts
                </Link>

                <Link href="/profile">
                    Profile
                </Link>

                <button
                    onClick={handleLogout}
                    className="text-red-400"
                >
                    Logout
                </button>

            </div>

        </nav>
    );
}