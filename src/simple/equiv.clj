;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns simple.equiv
  (:use [tawny.owl])
  (:require [tawny.reasoner :as r]))

;; ## Introduction
;;
;; This is one of the simplest examples of an inference. In this case, we have
;; two nearly identical classes one of which is primitive and one defined.

;; ## Preamble
;;
;; Define the reasoner factory and the ontology. Shut the reasoner up, or it
;; will open a GUI.
(r/reasoner-factory :hermit)
(r/reasoner-silent)

(defontology equiv)

;; ## Supporting classes
;;
;; We define an object property called `r` and a class called `X`.
(defoproperty r)
(defclass X)

;; ## Main classes
;;
;; `B` is defined as being the subclass of the anonymous classes `(owlsome r
;; X)`. This states that for every `B` there is at least one `X` which is related
;; to `B` by the `r` property.
(defclass B
  :subclass (owlsome r X))

;; `A` is defined as being equivalent to the same anonymous class. This is a
;; two way implication. Anything that is asserted to be a subclass of `A`
;; would have an `X` which is related to it by `r`. Additionally, though,
;; anything that has `X` related by an `r` is also a subclass of `A`. This
;; includes class `B`.
(defclass A
  :equivalent (owlsome r X))

;; ## Inferencing
;;
;; Checking for subclasses of `A` without reasoner shows no subclasses, as `B`
;; has not been asserted to be a subclass of `A`
(subclasses A)

;; However, reasoning shows that `B` can be infered to be a subclass of `A`
(r/isubclasses A)


(defclass D
  :subclass (only r X))

(defclass C
  :equivalent (only r X))

(subclasses C)
(r/isubclasses C)

(defindividual i)

(defclass F
  :subclass (hasvalue r i))

(defclass E
  :equivalent (hasvalue r i))

(subclasses E)
(r/isubclasses E)
